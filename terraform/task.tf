resource "aws_ecs_task_definition" "task" {
  family = "TSK-${var.projectName}"
  container_definitions = jsonencode([
    {
      name      = "${var.projectName}"
      essential = true,
      image     = "${aws_ecr_repository.repository.repository_url}:latest",
      #command   = ["-Dsonar.search.javaAdditionalOpts=-Dnode.store.allow_mmap=false"]
      command   = ["java", "-jar", "/app/target/fastburger-0.2.1-SNAPSHOT.jar", "--spring.config.name=docker"]
      environment = [
        {
          name = "SPRING_DATASOURCE_URL"
          #value = "jdbc:postgresql://${data.aws_db_instance.database.endpoint}/${aws_db_instance.rds.db_name}"
          value = "jdbc:postgresql://${aws_db_instance.rds.identifier}/${aws_db_instance.rds.db_name}"
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
          value = "8080"
        },
        {
          name  = "MERCADO_TOKEN"
          value = "Bearer TEST-8984958385382549-010720-8b45e680cd085f9976863a95655daff7-143716477"
        },
        {
          name  = "TOKEN_FALLBACK"
          value = "ODk4NDk1ODM4NTM4MjU0OS0wMTA3MjAtOGI0NWU2ODBjZDA4NWY5OTc2ODYzYTk1NjU1ZGFmZjctMTQzNzE2NDc3"
        },
        {
          name  = "MERCADO_API"
          value = "https://api.mercadopago.com"
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