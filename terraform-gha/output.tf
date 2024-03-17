output "rds-name" {
  value = "rds-${var.projectName}"
}

output "rds-endpoint" {
  value = aws_db_instance.rds.endpoint
}

output "dns_name" {
  description = "The DNS name of the load balancer"
  value       = aws_lb.alb.dns_name
}