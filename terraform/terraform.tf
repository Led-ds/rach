terraform {
  backend "s3" { #Foi criado um usuário na aws para o terraform atuar no provisionamento
    bucket = "rach-terraform-state"
    key = "rach-api-app"
    region = "us-east-1"
    profile = "terraform"
  }
}