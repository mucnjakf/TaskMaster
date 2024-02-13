using System.Net.Http.Json;
using TaskMasterWeb.Client.Dtos.Project;
using TaskMasterWeb.Client.Services.Contracts;

namespace TaskMasterWeb.Client.Services;

public class ProjectsService : IProjectsService
{
    private readonly HttpClient _httpClient;

    public ProjectsService(HttpClient httpClient)
    {
        _httpClient = httpClient;
    }

    public async Task<IEnumerable<ProjectDto>> GetProjectsAsync()
    {
        HttpResponseMessage httpResponseMessage = await _httpClient.GetAsync("api/projects");

        if (!httpResponseMessage.IsSuccessStatusCode)
        {
            throw new Exception();
        }

        IEnumerable<ProjectDto>? projectDtos = await httpResponseMessage.Content.ReadFromJsonAsync<IEnumerable<ProjectDto>>();

        return projectDtos!;
    }

    public async Task CreateProjectAsync(ProjectCreateDto projectCreateDto)
    {
        HttpResponseMessage httpResponseMessage = await _httpClient.PostAsJsonAsync("api/projects", projectCreateDto);

        if (!httpResponseMessage.IsSuccessStatusCode)
        {
            throw new Exception();
        }
    }

    public async Task UpdateProjectAsync(int id, ProjectUpdateDto projectUpdateDto)
    {
        HttpResponseMessage httpResponseMessage = await _httpClient.PutAsJsonAsync($"api/projects/{id}", projectUpdateDto);

        if (!httpResponseMessage.IsSuccessStatusCode)
        {
            throw new Exception();
        }
    }

    public async Task DeleteProjectAsync(int id)
    {
        HttpResponseMessage httpResponseMessage = await _httpClient.DeleteAsync($"api/projects/{id}");

        if (!httpResponseMessage.IsSuccessStatusCode)
        {
            throw new Exception();
        }
    }
}