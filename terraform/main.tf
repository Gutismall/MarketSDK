provider "aws" {
  region = var.aws_region
}

resource "aws_db_instance" "market_db" {
  identifier           = "market-db"
  engine               = "postgres"
  instance_class       = "db.t3.micro"
  allocated_storage    = 5
  storage_type         = "gp2"
  username             = var.db_username
  password             = var.db_password
  db_name              = var.db_name
  publicly_accessible  = true
  skip_final_snapshot  = true
}

resource "aws_elastic_beanstalk_application" "spring_app" {
  name        = "market-api"
  description = "Spring Boot backend for Market system"
}

resource "aws_elastic_beanstalk_environment" "spring_env" {
  name                = "market-api-env"
  application         = aws_elastic_beanstalk_application.spring_app.name
  solution_stack_name = "64bit Amazon Linux 2 v5.8.4 running Corretto 17"

  setting {
    namespace = "aws:elasticbeanstalk:application:environment"
    name      = "RDS_HOSTNAME"
    value     = aws_db_instance.market_db.address
  }

  setting {
    namespace = "aws:elasticbeanstalk:application:environment"
    name      = "RDS_PORT"
    value     = var.rds_port
  }

  setting {
    namespace = "aws:elasticbeanstalk:application:environment"
    name      = "RDS_DB_NAME"
    value     = var.db_name
  }

  setting {
    namespace = "aws:elasticbeanstalk:application:environment"
    name      = "RDS_USERNAME"
    value     = var.db_username
  }

  setting {
    namespace = "aws:elasticbeanstalk:application:environment"
    name      = "RDS_PASSWORD"
    value     = var.db_password
  }

  setting {
    namespace = "aws:elasticbeanstalk:application:environment"
    name      = "SERVER_PORT"
    value     = var.spring_app_port
  }
}

resource "aws_amplify_app" "dashboard_app" {
  name       = "market-dashboard"
  repository = var.frontend_repo_url

  environment_variables = {
    NEXT_PUBLIC_API_URL = "http://${aws_elastic_beanstalk_environment.spring_env.endpoint_url}/api"
  }

  oauth_token = var.github_token
}

resource "aws_amplify_branch" "main" {
  app_id = aws_amplify_app.dashboard_app.id
  branch_name = "main"
}
