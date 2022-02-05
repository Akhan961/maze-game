package com.mycompany.app;

//responsible for each block on the map
public class Block implements Entity {


    /*
     * These are attributes for this class
     *      Private, so only Block.java has access to it therefore allowed public getters and setters
     *
     * element = the symbol shown on map
     *          ( * = walls, b = barrels, k = keys, p = punishments, c = coins, s = starting door
                  e = ending door, and m = mario)
     * x = the row position of the block
     * y = the column position of the block
     */

    private String element;
    public String temp_element =" ";
    private int x;
    private int y;

     /*
     * This constructor is responsible for creating a block on the map
     *
     * @param   none       takes no parameters
     * @return  nothing    sets the element to empty space
     *                     sets the x or row position to 0
     *                     sets the y or column position to 0
     */
    public Block(){
        this.element = " ";
        this.x = 0;
        this.y = 0;
    }
    /*
     * This constructor is responsible for creating a block on the map
     *      with a given element to then be able to set the element as specified
     *
     * @param   element    takes a char value to set the block element to that (in setter)
     * @return  nothing    sets the element position to the entered element
     */
    public Block(String element) {
        this.element = element;
    }
    /*
     * This is a getter which gives the value of X or the row position of the block
     *      It overrides/ implements the Entity which is an interface
     *
     * @param   none    takes no parameters
     * @return  x       the row position of the block
     */
    public Block(char charAt) {
        this.element = String.valueOf(charAt);
    }

    @Override
    public int getX() {
        return x;
    }

    /*
     * This is a setter which sets the value of X or the row position of the block
     *      It overrides/ implements the Entity which is an interface
     *
     * @param   x   the new location for the row
     * @return  x   the row position of the block
     */
    @Override
    public void setX(int x) {
        this.x = x;
    }

    /*
     * This is a getter which gives the value of Y or the column position of the block
     *      It overrides/ implements the Entity which is an interface
     *
     * @param   none    takes no parameters
     * @return  y       the column position of the block
     */
    @Override
    public int getY() {
        return y;
    }

    /*
     * This is a setter which sets the value of Y or the column position of the block
     *      It overrides/ implements the Entity which is an interface
     *
     * @param   y   the new location for the column
     * @return  y   the column position of the block
     */
    @Override
    public void setY(int y) {
        this.y = y;
    }

     /*
     * This is a getter which gives the element of the block
     *      It overrides/ implements the Entity which is an interface
     *
     * @param   none        takes no parameters
     * @return  element     the value of the element
     */
    @Override
    public String getElement() {
        return String.valueOf(element);
    }

     /*
     * This is a setter which sets the element to a new char to be used on the block
     *      It overrides/ implements the Entity which is an interface
     *
     * @param   new_char   the new char for the element on the block
     * @return  element   updated the element so it applies the new char value
     */
    @Override
    public void setElement(String new_char){
        element = new_char;
    }


    /*
     * This is a setter for Temp
     *
     * @param   new_char   the new char for the element on the block
     * @return  element   updated the element so it applies the new char value
     */
    public void setTemp(String new_char){
        temp_element = element;
        element = new_char;
    }

    /*
     * This is a getter
     *
     * @param   nothing   takes no parameters
     * @return  element   updated the element so it applies the new char value
     */
    public String getTemp(){
        return String.valueOf(temp_element);
    }

    /*
     * This is a temporary value which gets removed
     *
     * @param   nothing   takes no parameters
     * @return  element   updated the element so it applies the new temp value
     */
    public void removeTemp(){
        element = temp_element;
    }

}
