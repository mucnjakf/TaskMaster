namespace TaskMasterWeb.Client.Dtos.User;

public record UserProjectDto
{
    public int Id { get; set; }

    public string Name { get; set; } = default!;

    public string Description { get; set; } = default!;
}