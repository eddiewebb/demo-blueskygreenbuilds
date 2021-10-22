module "eks" {
  source          = "terraform-aws-modules/eks/aws"
  cluster_name    = local.cluster_name
  cluster_version = "1.20"
  subnets         = [
      "subnet-ea15b081","subnet-3fb5cf73","subnet-894c5ef3"
  ]
  tags = {
    Environment = "demo"
    owner  = "eddie@circleci.com"
    purpose   = "SE demos of vamp"
    expiration = "2022-12-31"
  }

  vpc_id = module.vpc.vpc_id
  cluster_create_security_group = false
  cluster_security_group_id = "sg-0723d3661e802426c"

  manage_cluster_iam_resources = false
  cluster_iam_role_name = "se-demo-cluster-with-vamp"

  manage_worker_iam_resources = false
  #iam_instance_profile_name = " se-demo-EKSNodeRole"

  worker_create_security_group = false
  worker_security_group_id = "sg-0723d3661e802426c"
}

data "aws_eks_cluster" "cluster" {
  name = module.eks.cluster_id
}

data "aws_eks_cluster_auth" "cluster" {
  name = module.eks.cluster_id
}

resource "aws_eks_node_group" "se-demo-node-group" {
  cluster_name    = local.cluster_name
  node_group_name = "se-demo-node-group-small"
  node_role_arn   = "arn:aws:iam::660990364978:role/se-demo-EKSNodeRole"
  #version = "1.17"

  launch_template {
    name    = aws_launch_template.se-demo-eks-launch-template.name
    version = "1"
  }
  subnet_ids = [
      "subnet-ea15b081","subnet-3fb5cf73","subnet-894c5ef3"
  ]

  tags = {
    Environment = "demo"
    owner  = "eddie@circleci.com"
    purpose   = "SE demos of vamp"
    expiration = "2022-12-31"
  }

  scaling_config {
    desired_size = 2
    max_size     = 1
    min_size     = 1
  }

  update_config {
    max_unavailable = 2
  }

  # Ensure that IAM Role permissions are created before and deleted after EKS Node Group handling.
  # Otherwise, EKS will not be able to properly delete EC2 Instances and Elastic Network Interfaces.
  depends_on = [
    aws_iam_role_policy_attachment.se-demo-AmazonEKSWorkerNodePolicy,
    aws_iam_role_policy_attachment.se-demo-AmazonEKS_CNI_Policy,
    aws_iam_role_policy_attachment.se-demo-AmazonEC2ContainerRegistryReadOnly,
    aws_launch_template.se-demo-eks-launch-template,
  ]
}

resource "aws_iam_role_policy_attachment" "se-demo-AmazonEKSWorkerNodePolicy" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSWorkerNodePolicy"
  role       = "arn:aws:iam::660990364978:role/se-demo-EKSNodeRole"
}

resource "aws_iam_role_policy_attachment" "se-demo-AmazonEKS_CNI_Policy" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKS_CNI_Policy"
  role       = "arn:aws:iam::660990364978:role/se-demo-EKSNodeRole"
}
resource "aws_iam_role_policy_attachment" "se-demo-AmazonEC2ContainerRegistryReadOnly" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEC2ContainerRegistryReadOnly"
  role       = "arn:aws:iam::660990364978:role/se-demo-EKSNodeRole"
}