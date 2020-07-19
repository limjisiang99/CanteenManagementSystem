
import control.MealControl;
import domain.Meal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NGO KIAN HEE
 */
public class staffChangeMealStatusServlet extends HttpServlet {
    private MealControl mealControl;


   public void init() throws ServletException {
        mealControl = new MealControl();
       
       
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String mealId = request.getParameter("mealId");
        String mealStatus = request.getParameter("mealStatus");
        Meal meal = new Meal();
        meal.setMealId(mealId);
        meal.setMealStatus(mealStatus);
        mealControl.changeMealStatus(meal);
        HttpSession session = request.getSession();
        session.setAttribute("Meals",mealControl.getMeals());
        request.setAttribute("mealStatusMessage","<font color=blue>Your meal status had changed!</font>");
        request.getRequestDispatcher("/staffChangeMealStatus.jsp").forward(request, response);
    }
}
