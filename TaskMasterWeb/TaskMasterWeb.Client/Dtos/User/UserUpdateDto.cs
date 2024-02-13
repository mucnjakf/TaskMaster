using System.ComponentModel.DataAnnotations;

namespace TaskMasterWeb.Client.Dtos.User;

public record UserUpdateDto
{
    [Required(ErrorMessage = "First name is required")]
    public string FirstName { get; set; } = default!;

    [Required(ErrorMessage = "Last name is required")]
    public string LastName { get; set; } = default!;
}