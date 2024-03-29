﻿using System.Security.Claims;
using System.Text.Json;

namespace TaskMasterWeb.Client.Services;

public static class JwtService
{
    public static IEnumerable<Claim> ParseClaimsFromJwt(string jwt)
    {
        List<Claim> claims = new();
        string payload = jwt.Split('.')[1];

        byte[] jsonBytes = ParseBase64WithoutPadding(payload);

        Dictionary<string, object>? keyValuePairs = JsonSerializer.Deserialize<Dictionary<string, object>>(jsonBytes);

        ExtractRolesFromJwt(claims, keyValuePairs);

        claims.AddRange(keyValuePairs!.Select(kvp => new Claim(kvp.Key, kvp.Value.ToString()!)));

        return claims;
    }

    private static void ExtractRolesFromJwt(List<Claim> claims, IDictionary<string, object>? keyValuePairs)
    {
        keyValuePairs!.TryGetValue("roles", out object? roles);

        if (roles != null)
        {
            string[] parsedRoles = roles.ToString()!.Trim().TrimStart('[').TrimStart('"').TrimEnd(']').TrimEnd('"').Split(',');

            if (parsedRoles.Length > 1)
            {
                claims.AddRange(parsedRoles.Select(parsedRole => new Claim(ClaimTypes.Role, parsedRole.Trim('"'))));
            }
            else
            {
                claims.Add(new Claim(ClaimTypes.Role, parsedRoles[0]));
            }

            keyValuePairs.Remove("roles");
        }
    }

    private static byte[] ParseBase64WithoutPadding(string base64)
    {
        switch (base64.Length % 4)
        {
            case 2:
                base64 += "==";
                break;
            case 3:
                base64 += "=";
                break;
        }

        return Convert.FromBase64String(base64);
    }
}