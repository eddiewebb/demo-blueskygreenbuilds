
#
# Non-standard launch templates to support EKS, its a shit show
# https://docs.aws.amazon.com/eks/latest/userguide/launch-templates.html
#

resource "aws_launch_template" "se-demo-eks-launch-template" {
  name = "se-demo-eks-launch-template"

  block_device_mappings {
    device_name = "/dev/sda1"

    ebs {
      volume_size = 20
    }
  }

  ebs_optimized = true




  #instance_type = "t2.small"


  metadata_options {
    http_endpoint               = "enabled"
    http_tokens                 = "required"
    http_put_response_hop_limit = 1
  }

  # even for private subnets, must have pulic IP https://github.com/aws/containers-roadmap/issues/607
  network_interfaces {
    associate_public_ip_address = true
  }
 
  tag_specifications {
    resource_type = "instance"

    
    tags = {
      Environment = "demo"
      owner  = "eddie@circleci.com"
      purpose   = "SE demos of vamp"
      expiration = "2022-12-31"
    }
  }
}