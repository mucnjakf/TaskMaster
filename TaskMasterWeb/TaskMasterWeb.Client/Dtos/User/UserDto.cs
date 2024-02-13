namespace TaskMasterWeb.Client.Dtos.User;

public record UserDto
{
    public int Id { get; set; }

    public string Email { get; set; } = default!;

    public string FirstName { get; set; } = default!;

    public string LastName { get; set; } = default!;

    public string Role { get; set; } = default!;

    public IEnumerable<UserProjectDto>? Projects { get; set; }

    public IEnumerable<UserTaskDto>? Tasks { get; set; }
}