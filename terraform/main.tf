provider "aws" { //Terraform vai prover a infra estrutura para AWS
  version = "~> 3.72"
  shared_credentials_files = [ abspath("~/.aws/credentials") ]
  profile = "terraform"
  region = "us-east-1"
}