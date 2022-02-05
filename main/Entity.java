package com.mycompany.app;

public interface Entity{

    // Block class functions

    /*
    * These are abstract methods in the interface that get overridden
    *      Abstract mean can only be used in an abstract class,
    *      and does not include a body. The body is instead implemented in the subclass
    */

    /*
    * this is abstract methods intended to be implemented by its subclasses
    *         will be used to access the x, or row position of the cell
    *
    * @param   none       takes no parameters
    * @return  nothing    has no body
    */
    abstract int getX();


    /*
     * This is abstract methods intended to be implemented by its subclasses
     *         It will be used to access the y, or column position of the cell
     *
     * @param   none        takes no parameters
     * @return  nothing     to be implemented in subclass
     */
    abstract int getY();

    /*
     * This is abstract methods intended to be implemented by its subclasses
     *         It will be used to set the x, or row position of the cell
     *
     * @param   x           takes new row position
     * @return  nothing    to be implemented by its subclasses
     */
    abstract void setX(int x);

    /*
     * This is abstract methods intended to be implemented by its subclasses
     *         will be used to set the y, or column position of the cell
     *
     * @param   y       takes new column position
     * @return  y       to be implemented by its subclasses
     */
    abstract void setY(int y);


    /*
     * This is abstract methods intended to be implemented by its subclasses
     *         will be used to access the element, or symbol of the cell
     *
     * @param   none        takes no parameters
     * @return  nothing     to be implemented by subclasses
     */
    abstract String getElement();

    /*
     * This is abstract methods intended to be implemented by its subclasses
     *         will be used to set the element, or symbol of the cell
     *
     * @param   new_char    to set the new char val
     * @return  nothing     to be implemented by subclasses
     */
    abstract void setElement(String new_char);

}
