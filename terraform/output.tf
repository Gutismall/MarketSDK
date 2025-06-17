output "rds_endpoint" {
  description = "The connection endpoint for the RDS database"
  value       = aws_db_instance.market_db.address
  sensitive   = true
}

output "elasticbeanstalk_app_url" {
  description = "URL of the deployed Spring Boot application on Elastic Beanstalk"
  value       = "http://${aws_elastic_beanstalk_environment.spring_env.endpoint_url}"
}

output "amplify_app_url" {
  description = "URL of the deployed Next.js dashboard on AWS Amplify"
  value       = aws_amplify_app.dashboard_app.default_domain
}