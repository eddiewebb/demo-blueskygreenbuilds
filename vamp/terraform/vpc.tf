variable "region" {
  default     = "us-east-2"
  description = "AWS region"
}

provider "aws" {
  region = var.region
  profile = "CustomerEngineeringPowerUser-660990364978"
}

data "aws_availability_zones" "available" {}

locals {
  cluster_name = "se-demo-eks-${random_string.suffix.result}"
}

resource "random_string" "suffix" {
  length  = 8
  special = false
}

module "vpc" {
  create_vpc = false #use existing VPC
  manage_default_vpc               = true
  default_vpc_name                 = "default"
  default_vpc_enable_dns_hostnames = true

  source  = "terraform-aws-modules/vpc/aws"
  version = "3.2.0"



  tags = {
    "kubernetes.io/cluster/${local.cluster_name}" = "shared"
  }

  public_subnet_tags = {
    "kubernetes.io/cluster/${local.cluster_name}" = "shared"
    "kubernetes.io/role/elb"                      = "1"
  }

  private_subnet_tags = {
    "kubernetes.io/cluster/${local.cluster_name}" = "shared"
    "kubernetes.io/role/internal-elb"             = "1"
  }
}
