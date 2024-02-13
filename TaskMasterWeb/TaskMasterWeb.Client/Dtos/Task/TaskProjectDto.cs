namespace TaskMasterWeb.Client.Dtos.Task;

public record TaskProjectDto
{
    public int Id { get; set; }

    public string Name { get; set; } = default!;

    public string Description { get; set; } = default!;

    public string Assignee { get; set; } = default!;
}