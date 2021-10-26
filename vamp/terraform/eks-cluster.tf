module "eks" {
  source          = "terraform-aws-modules/eks/aws"
  cluster_name    = local.cluster_name
  cluster_version = "1.20"
  subnets         = ["subnet-03d02b4de5a7a5f01",resource.aws_subnet.se-demo-eks-subnet-2b.id]
  tags = {
    Environment = "demo"
    owner  = "eddie@circleci.com"
    purpose   = "SE demos of vamp"
    expiration = "2022-12-31"
  }

  vpc_id     = module.vpc.default_vpc_id

  # stop spawning lots of roles since stupid SSO permissions wont let us delete them
  manage_cluster_iam_resources = false
  cluster_iam_role_name = "se-demo-cluster-with-vamp"

  manage_worker_iam_resources = false
  #iam_instance_profile_name = "se-demo-EKSNodeRole"


  # we can disable sg creation as well, but we ccan delete these if we go wrong.
  #cluster_create_security_group = false
  #cluster_security_group_id = "sg-0723d3661e802426c"
  #worker_create_security_group = false
  #worker_security_group_id = "sg-0723d3661e802426c"

# Worker Groups are defined below as node group with custom launch template (to tag per CE policy)

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
  ami_type = "AL2_x86_64"

  launch_template {
    name    = aws_launch_template.se-demo-eks-launch-template.name
    version = aws_launch_template.se-demo-eks-launch-template.latest_version
  }
  #subnet_ids = module.eks.subnets[*].id
  subnet_ids = ["subnet-03d02b4de5a7a5f01",resource.aws_subnet.se-demo-eks-subnet-2b.id]

  tags = {
    Environment = "demo"
    owner  = "eddie@circleci.com"
    purpose   = "SE demos of vamp"
    expiration = "2022-12-31"
  }

  scaling_config {
    desired_size = 2
    max_size     = 2
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
  role       = "se-demo-EKSNodeRole"
}

resource "aws_iam_role_policy_attachment" "se-demo-AmazonEKS_CNI_Policy" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKS_CNI_Policy"
  role       = "se-demo-EKSNodeRole"
}
resource "aws_iam_role_policy_attachment" "se-demo-AmazonEC2ContainerRegistryReadOnly" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEC2ContainerRegistryReadOnly"
  role       = "se-demo-EKSNodeRole"
}