using System.ComponentModel.DataAnnotations;

namespace TaskMasterWeb.Client.Dtos.Project;

public record ProjectUpdateDto
{
    [Required(ErrorMessage = "Name is required")]
    public string Name { get; set; } = default!;

    [Required(ErrorMessage = "Description is required")]
    public string Description { get; set; } = default!;
}