variable "projectName" {
  default = "fastburger"
}

variable "clusterName" {
  default = "FastBurger"
}

variable "regionDefault" {
  default = "us-east-1" # example: us-east-1
}

variable "engineRds" {
  default = "postgres"
}

variable "engineRdsVersion" {
  default = "13.10"
}

variable "rdsDbName" {
  default = "challenge"
}

variable "rdsUser" {
  default = "arch"
}

variable "rdsPass" {
  default = "challenge"
}

variable "instanceClass" {
  default = "db.t3.micro"
}

variable "storageType" {
  default = "gp3"
}

variable "minStorage" {
  default = "20"
}

variable "maxStorage" {
  default = "30"
}

variable "subnet01" {
  default = "subnet-03e972fa6d874e055" # example: subnet-abcdef1
}

variable "subnet02" {
  default = "subnet-0f4b81d5b1fda41d6" # example: subnet-abcdef1
}

variable "subnet03" {
  default = "subnet-04e2287e1f14f9416" # example: subnet-abcdef1
}

variable "vpcId" {
  #default = "vpc-0ab35d5fa67a46c53" # example: vpc-abcdef1
  default = "vpc-0a8292bdf36eeb88e" # example: vpc-abcdef1
}

variable "vpcCIDR" {
  default = "172.31.0.0/16" # example: 10.10.0.0/16
}

variable "AWSAccount" {
  default = "339712887556" # example: 123456789
}

variable "tags" {
  type = map(string)
  default = {
    App      = "fastburger",
    Ambiente = "Desenvolvimento"
  }
}
