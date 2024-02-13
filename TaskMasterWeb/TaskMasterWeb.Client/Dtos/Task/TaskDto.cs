namespace TaskMasterWeb.Client.Dtos.Task;

public record TaskDto
{
    public int Id { get; set; }

    public string Name { get; set; } = default!;

    public string Status { get; set; } = default!;

    public string Priority { get; set; } = default!;

    public TaskProjectDto Project { get; set; } = default!;

    public TaskUserDto Assignee { get; set; } = default!;
}