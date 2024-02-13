using TaskMasterWeb.Client.Dtos.Project;
using TaskMasterWeb.Client.Dtos.Task;

namespace TaskMasterWeb.Client.Services.Contracts;

public interface IProjectsService
{
    Task<IEnumerable<ProjectDto>> GetProjectsAsync();
    
    Task CreateProjectAsync(ProjectCreateDto projectCreateDto);

    Task UpdateProjectAsync(int id, ProjectUpdateDto projectUpdateDto);

    Task DeleteProjectAsync(int id);
}