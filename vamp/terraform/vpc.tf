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


resource "aws_subnet" "se-demo-eks-subnet-2b" {
  vpc_id     = module.vpc.default_vpc_id
  cidr_block = "172.31.102.0/24"
  map_public_ip_on_launch = true

  availability_zone = "us-east-2b"

  tags = {
    Name = "se-demo-eks-public-subnet-2b"
    Environment = "demo"
    owner  = "eddie@circleci.com"
    purpose   = "SE demos of vamp"
    expiration = "2022-12-31"
    "kubernetes.io/cluster/${local.cluster_name}" = "shared"
    "kubernetes.io/role/elb"             = "1"
  }
}

resource "aws_subnet" "se-demo-eks-subnet-2a" {
  vpc_id     = module.vpc.default_vpc_id
  cidr_block = "172.31.101.0/24"
  map_public_ip_on_launch = true

  availability_zone = "us-east-2a"

  tags = {
    Name = "se-demo-eks-subnet-2a"
    Environment = "demo"
    owner  = "eddie@circleci.com"
    purpose   = "SE demos of vamp"
    expiration = "2022-12-31"
    "kubernetes.io/cluster/${local.cluster_name}" = "shared"
    "kubernetes.io/role/elb"             = "1"
  }
}