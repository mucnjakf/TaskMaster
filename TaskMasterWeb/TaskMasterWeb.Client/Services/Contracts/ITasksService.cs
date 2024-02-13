using TaskMasterWeb.Client.Dtos.Task;

namespace TaskMasterWeb.Client.Services.Contracts;

public interface ITasksService
{
    Task<IEnumerable<TaskDto>> GetTasksAsync();

    Task<IEnumerable<TaskDto>> GetProjectTasksAsync(int projectId);
    
    Task CreateTaskAsync(TaskCreateDto taskCreateDto);

    Task UpdateTaskAsync(int id, TaskUpdateDto taskUpdateDto);

    Task DeleteTaskAsync(int id);
}