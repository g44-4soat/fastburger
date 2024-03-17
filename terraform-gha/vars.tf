variable "projectName" {
  default = "fastburger"
}

variable "clusterName" {
  default = "FastBurger"
}

variable "regionDefault" {
  default = "us-east-1"
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

#variable "rdsUser" {
#  description = "it cames from gh secrets"
#  default     = var.rdsuser
#}

#variable "rdsPass" {
#  description = "it cames from gh secrets"
#  default     = var.rdspass
#}

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

variable "spring_port" {
  default = "8080"
}

#variable "mercado_token" {
#  description = "it cames from gh secrets"
#  default     = var.mercado_pago
#}

variable "token_fallback" {
  #default = "ODk4NDk1ODM4NTM4MjU0OS0wMTA3MjAtOGI0NWU2ODBjZDA4NWY5OTc2ODYzYTk1NjU1ZGFmZjctMTQzNzE2NDc5"
  default = "ODk4NDk1ODM4NTM4MjU0OS0wMTA3MjAtOGI0NWU2ODBjZDA4NWY5OTc2ODYzYTk1NjU1ZGFmZjctMTQzNzE2NDc3"
}

variable "mercado_api" {
  default = "https://api.mercadopago.com"
}

variable "subnet01" {
  default = "subnet-03e972fa6d874e055"
}

variable "subnet02" {
  default = "subnet-0f4b81d5b1fda41d6"
}

variable "subnet03" {
  default = "subnet-04e2287e1f14f9416"
}

variable "vpcId" {
  default = "vpc-0a8292bdf36eeb88e"
}

variable "vpcCIDR" {
  default = "172.31.0.0/16"
}

variable "AWSAccount" {
  default = "339712887556"
}

variable "tags" {
  type = map(string)
  default = {
    Project     = "Fiap Tech Challenge 4SOAT - g44"
    App         = "fastburger",
    Environment = "Desenvolvimento"
    ManagedBy   = "terraform"
  }
}
