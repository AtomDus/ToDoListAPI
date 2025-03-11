package be.bdus.utils.filters;

public record UserFilter (
        String firstName,
        String lastName,
        Integer minAge,
        Integer maxAge
){
}
