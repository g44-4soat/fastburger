resource "aws_ecs_task_definition" "task" {
  family = "TSK-${var.projectName}"
  container_definitions = jsonencode([
    {
      name      = "${var.projectName}"
      essential = true,
      image     = "${aws_ecr_repository.repository.repository_url}:latest",
      command   = ["java", "-jar", "/app/target/fastburger-0.2.1-SNAPSHOT.jar", "--spring.config.name=docker"]
      environment = [
        {
          name  = "SPRING_DATASOURCE_URL"
          value = "jdbc:postgresql://${aws_db_instance.rds.endpoint}/${aws_db_instance.rds.db_name}"
        },
        {
          name  = "SPRING_DATASOURCE_USERNAME"
          value = "${var.rdsUser}"
        },
        {
          name  = "SPRING_DATASOURCE_PASSWORD"
          value = "${var.rdsPass}"
        },
        {
          name  = "SPRING_PORT"
          value = "${var.spring_port}"
        },
        {
          name  = "MERCADO_TOKEN"
          value = "${var.mercado_token}"
        },
        {
          name  = "TOKEN_FALLBACK"
          value = "${var.token_fallback}"
        },
        {
          name  = "MERCADO_API"
          value = "${var.mercado_api}"
        }
      ]
      logConfiguration = {
        logDriver = "awslogs"
        options = {
          awslogs-group         = "${aws_cloudwatch_log_group.cloudwatch-log-group.name}"
          awslogs-region        = "${var.regionDefault}"
          awslogs-stream-prefix = "ecs"
        }
      }
      portMappings = [
        {
          containerPort = 8080
          hostPort      = 8080
          protocol      = "tcp"
        }
      ]
    }
  ])
  network_mode = "awsvpc"

  requires_compatibilities = ["FARGATE"]

  execution_role_arn = "arn:aws:iam::${var.AWSAccount}:role/ecsTaskExecutionRole-1"

  memory = "4096"
  cpu    = "2048"

  depends_on = [aws_db_instance.rds]
}
