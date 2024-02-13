namespace TaskMasterWeb.Client.Dtos.Project
{
    public record ProjectDto
    {
        public int Id { get; set; }

        public string Name { get; set; } = default!;

        public string Description { get; set; } = default!;

        public IEnumerable<ProjectTaskDto>? Tasks { get; set; }

        public ProjectUserDto Assignee { get; set; } = default!;
    }
}