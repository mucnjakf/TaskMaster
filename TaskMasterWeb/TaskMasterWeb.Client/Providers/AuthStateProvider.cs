using System.Net.Http.Headers;
using System.Security.Claims;
using Blazored.LocalStorage;
using Microsoft.AspNetCore.Components.Authorization;
using TaskMasterWeb.Client.Services;

namespace TaskMasterWeb.Client.Providers;

public class AuthStateProvider : AuthenticationStateProvider
{
    private readonly HttpClient _httpClient;
    private readonly ILocalStorageService _localStorageService;
    private readonly AuthenticationState _anonymous;

    public AuthStateProvider(HttpClient httpClient, ILocalStorageService localStorageService)
    {
        _httpClient = httpClient;
        _localStorageService = localStorageService;
        _anonymous = new AuthenticationState(new ClaimsPrincipal(new ClaimsIdentity()));
    }

    public override async Task<AuthenticationState> GetAuthenticationStateAsync()
    {
        string? jwt = await _localStorageService.GetItemAsync<string>("jwt");

        if (string.IsNullOrWhiteSpace(jwt))
        {
            return _anonymous;
        }

        _httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", jwt);

        return new AuthenticationState(new ClaimsPrincipal(new ClaimsIdentity(JwtService.ParseClaimsFromJwt(jwt), "jwtAuthType")));
    }

    public void NotifyUserAuthentication(string jwt)
    {
        ClaimsPrincipal authenticatedUser = new(new ClaimsIdentity(JwtService.ParseClaimsFromJwt(jwt), "jwtAuthType"));

        Task<AuthenticationState> authState = Task.FromResult(new AuthenticationState(authenticatedUser));

        NotifyAuthenticationStateChanged(authState);
    }

    public void NotifyUserLogout()
    {
        Task<AuthenticationState> authState = Task.FromResult(_anonymous);

        NotifyAuthenticationStateChanged(authState);
    }
}