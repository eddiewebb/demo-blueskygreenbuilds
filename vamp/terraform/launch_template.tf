resource "aws_launch_template" "se-demo-eks-launch-template" {
  name = "se-demo-eks-launch-template"

  block_device_mappings {
    device_name = "/dev/sda1"

    ebs {
      volume_size = 20
    }
  }

  ebs_optimized = true




  instance_type = "t2.small"


  metadata_options {
    http_endpoint               = "enabled"
    http_tokens                 = "required"
    http_put_response_hop_limit = 1
  }


  vpc_security_group_ids = ["sg-0f908b402bbaef9d9"]
 
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