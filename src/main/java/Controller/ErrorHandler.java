package Controller;

import Model.AccountSession;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface ErrorHandler {

  default void authenticate(HttpSession session) throws InvalidRequestException {
    if (session == null || session.getAttribute("accountSession") == null) {
      throw new InvalidRequestException(
          "Authentication Error",
          List.of("Not Authenticated"),
          HttpServletResponse.SC_UNAUTHORIZED);
    }
  }

  default void authorize(HttpSession session) throws InvalidRequestException {
    authenticate(session);
    AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
    if (!accountSession.isAdmin()) {
      throw new InvalidRequestException(
          "Authorization Error", List.of("Action Not Allowed"), HttpServletResponse.SC_FORBIDDEN);
    }
  }

  default void internalError() throws InvalidRequestException {
    List<String> errors = List.of("Unhandled Error", "Try Again Later");
    throw new InvalidRequestException(
        "Internal Error", errors, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }

  default void notFound() throws InvalidRequestException {
    throw new InvalidRequestException(
        "Internal Error",
        List.of("Resource Not Found"),
        HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }

  default void notAllowed() throws InvalidRequestException {
    throw new InvalidRequestException(
        "Action Not Allowed",
        List.of("Action Not Allowed"),
        HttpServletResponse.SC_METHOD_NOT_ALLOWED);
  }
}
