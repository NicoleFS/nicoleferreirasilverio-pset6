# nicoleferreirasilverio-pset6

Final assignment for the Native App Studio course from the Minor Programming at the UvA

This app lets users search for recipes by entering ingredients. The data will be requested from the food2fork API by several
Http request helpers and AsyncTasks and will be shown in a ListView in the MainActivity. 

From the MainActivity users can either click a button to go to the login page, where a user can sign in or sign up by e-mail through
firebase, click a button to go to the grocery list, where needed ingredients will be shown in a list, or click on a recipe, which leads
the user to an activity which shows more details about the chosen recipe. In this activity the user can click a link to visit the 
instructions on the original publisher's website and sees the ingredients needed in a list. The user can click on the ingredients
they need to buy, to send these to the grocery list, where the user can check/uncheck and remove items from the grocery list.
