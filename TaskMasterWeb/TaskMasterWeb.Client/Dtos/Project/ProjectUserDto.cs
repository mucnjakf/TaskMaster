﻿namespace TaskMasterWeb.Client.Dtos.Project;

public record ProjectUserDto
{
    public int Id { get; set; }

    public string Email { get; set; } = default!;

    public string FullName { get; set; } = default!;
}