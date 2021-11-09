package controllers;

import play.mvc.Result;

import static play.mvc.Results.ok;

public class HomeController {
    public Result index() {
        return ok(views.html.index.render());
    }
}
