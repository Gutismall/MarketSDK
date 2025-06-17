variable "db_username" {
  description = "Username for the RDS database"
  type        = string
  sensitive   = true
}

variable "db_password" {
  description = "Password for the RDS database"
  type        = string
  sensitive   = true
}

variable "db_name" {
  description = "Initial database name"
  type        = string
  default     = "market"
}

variable "frontend_repo_url" {
  description = "GitHub repository URL of the Next.js dashboard"
  type        = string
}

variable "github_token" {
  description = "GitHub token for accessing the frontend repository"
  type        = string
  sensitive   = true
}

variable "spring_app_port" {
  description = "The port Spring Boot application runs on"
  type        = number
  default     = 5000
}