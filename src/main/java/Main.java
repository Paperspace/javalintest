import io.javalin.Javalin;

public class Main
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> ctx.result("Hello World"));
    }
}
