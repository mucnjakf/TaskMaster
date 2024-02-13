namespace TaskMasterWeb.Client.Dtos.User;

public record AuthenticationDto
{
    public string Token { get; set; } = default!;
}