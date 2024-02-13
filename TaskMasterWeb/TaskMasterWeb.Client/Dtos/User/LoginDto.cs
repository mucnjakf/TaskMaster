using System.ComponentModel.DataAnnotations;

namespace TaskMasterWeb.Client.Dtos.User;

public record LoginDto
{
    [Required(ErrorMessage = "Email is required")]
    public string Email { get; set; } = default!;

    [Required(ErrorMessage = "Password is required")]
    public string Password { get; set; } = default!;
}