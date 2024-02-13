using System.Net.Http.Json;
using TaskMasterWeb.Client.Dtos.Task;
using TaskMasterWeb.Client.Services.Contracts;

namespace TaskMasterWeb.Client.Services;

public class TasksService : ITasksService
{
    private readonly HttpClient _httpClient;

    public TasksService(HttpClient httpClient)
    {
        _httpClient = httpClient;
    }

    public async Task<IEnumerable<TaskDto>> GetTasksAsync()
    {
        HttpResponseMessage httpResponseMessage = await _httpClient.GetAsync("api/tasks");

        if (!httpResponseMessage.IsSuccessStatusCode)
        {
            throw new Exception();
        }

        IEnumerable<TaskDto>? taskDtos = await httpResponseMessage.Content.ReadFromJsonAsync<IEnumerable<TaskDto>>();

        return taskDtos!;
    }

    public async Task<IEnumerable<TaskDto>> GetProjectTasksAsync(int projectId)
    {
        HttpResponseMessage httpResponseMessage = await _httpClient.GetAsync($"api/tasks?projectId={projectId}");

        if (!httpResponseMessage.IsSuccessStatusCode)
        {
            throw new Exception();
        }

        IEnumerable<TaskDto>? taskDtos = await httpResponseMessage.Content.ReadFromJsonAsync<IEnumerable<TaskDto>>();

        return taskDtos!;
    }

    public async Task CreateTaskAsync(TaskCreateDto taskCreateDto)
    {
        HttpResponseMessage httpResponseMessage = await _httpClient.PostAsJsonAsync("api/tasks", taskCreateDto);

        if (!httpResponseMessage.IsSuccessStatusCode)
        {
            throw new Exception();
        }
    }

    public async Task UpdateTaskAsync(int id, TaskUpdateDto taskUpdateDto)
    {
        HttpResponseMessage httpResponseMessage = await _httpClient.PutAsJsonAsync($"api/tasks/{id}", taskUpdateDto);

        if (!httpResponseMessage.IsSuccessStatusCode)
        {
            throw new Exception();
        }
    }

    public async Task DeleteTaskAsync(int id)
    {
        HttpResponseMessage httpResponseMessage = await _httpClient.DeleteAsync($"api/tasks/{id}");

        if (!httpResponseMessage.IsSuccessStatusCode)
        {
            throw new Exception();
        }
    }
}