using System.ComponentModel.DataAnnotations;
using TaskMasterWeb.Client.Enums;
using TaskStatus = TaskMasterWeb.Client.Enums.TaskStatus;

namespace TaskMasterWeb.Client.Dtos.Task;

public record TaskUpdateDto
{
    [Required(ErrorMessage = "Name is required")]
    public string Name { get; set; } = default!;

    [Required(ErrorMessage = "Status is required")]
    public TaskStatus Status { get; set; }

    [Required(ErrorMessage = "Priority is required")]
    public TaskPriority Priority { get; set; }
}