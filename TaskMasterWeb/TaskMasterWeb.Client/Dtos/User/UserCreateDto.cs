using System.ComponentModel.DataAnnotations;

namespace TaskMasterWeb.Client.Dtos.User;

public record UserCreateDto
{
    [Required(ErrorMessage = "Email is required")]
    public string Email { get; set; } = default!;

    [Required(ErrorMessage = "Password is required")]
    public string Password { get; set; } = default!;

    [Required(ErrorMessage = "First name is required")]
    public string FirstName { get; set; } = default!;

    [Required(ErrorMessage = "Last name is required")]
    public string LastName { get; set; } = default!;

    public string Role { get; set; } = default!;
}