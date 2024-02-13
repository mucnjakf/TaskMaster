namespace TaskMasterWeb.Client.Dtos.Report;

public class ReportDto
{
    public int Id { get; set; }

    public string GeneratedAt { get; set; } = default!;

    public int TotalTasks { get; set; }

    public int TasksInBacklog { get; set; }

    public int TasksInProgress { get; set; }

    public int TasksCompleted { get; set; }
}