namespace TaskMasterWeb.Client.Dtos.User;

public record UserTaskDto
{
    public int Id { get; set; }

    public string Name { get; set; } = default!;

    public string Status { get; set; } = default!;

    public string Priority { get; set; } = default!;
}