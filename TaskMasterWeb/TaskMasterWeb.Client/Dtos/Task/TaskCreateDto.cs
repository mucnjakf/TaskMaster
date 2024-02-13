using System.ComponentModel.DataAnnotations;
using TaskMasterWeb.Client.Enums;
using TaskStatus = TaskMasterWeb.Client.Enums.TaskStatus;

namespace TaskMasterWeb.Client.Dtos.Task;

public record TaskCreateDto
{
    [Required(ErrorMessage = "Name is required")]
    public string Name { get; set; } = default!;

    [Required(ErrorMessage = "Status is required")]
    public TaskStatus Status { get; set; }

    [Required(ErrorMessage = "Priority is required")]
    public TaskPriority Priority { get; set; }

    [Required(ErrorMessage = "Project is required")]
    public int ProjectId { get; set; }

    [Required(ErrorMessage = "Assignee is required")]
    public int AssigneeId { get; set; }
}