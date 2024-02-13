namespace TaskMasterWeb.Client.Dtos.Task;

public record TaskUserDto
{
    public int Id { get; set; }

    public string Email { get; set; } = default!;

    public string FullName { get; set; } = default!;
}