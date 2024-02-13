namespace TaskMasterWeb.Client.Dtos.Project;

public record ProjectTaskDto
{
    public int Id { get; set; }

    public string Name { get; set; } = default!;

    public string Status { get; set; } = default!;

    public string Priority { get; set; } = default!;

    public string Assignee { get; set; } = default!;
}