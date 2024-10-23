/**
* A class that represents a generic term, statement and confidence score 
* @author Bonani
*/
public class Generic {
    private String term; /**The term of the generic*/
    private String statement; /**The statement of the generic*/
    private double cfScore; /**The confidence score of the generic*/

/**
* Creates a new Generic object with the specified term statement and confidence score.
* @param term the term of the generic
* @param statement the statement of the generic
* @param cfScore the confidence score of the generic
*/
    public Generic(String term, String statement, double cfScore) {
        this.term = term;
        this.statement = statement;
        this.cfScore = cfScore;
    }
    
/** Returns the term of the generic
* @return the term of the generic
*/
    public String getTerm() {

        return this.term;
    }
    
/** Returns the statement of the generic
* @return the statement of the generic
*/ 
    public String getStatement() {

        return this.statement;
    }
    
/** Returns the confidence score of the generic
* @return the confidence score of the generic
*/ 
    public double getCfScore() {

        return this.cfScore;
    }
    
/** 
* Sets the term of the generic to the given term.   
* @param termOther the new term the user wants to set
*/
    public void setTerm(String termOther) {

        this.term = termOther;
    }
    
/** 
* Sets the statement of the generic to the given statement.   
* @param statementOther the new statement the user wants to set
*/ 
    public void setStatement(String statementOther) {

        this.statement = statementOther;
    }
    
/** 
* Sets the confidence score of the generic to the given confidence scor.   
* @param cfScoreOther the new confidence score the user wants to set
*/ 
    public void setCfScore(double cfScoreOther) {

        this.cfScore = cfScoreOther;
    }
    
/**
* Checks if any two statements are equal.
* @param other the other statement that is being checked for equality
* @return checks whether statements are equal or not
*/
    public boolean equalStatement(String other) {
        return getStatement().equals(other);
    }
    
/** Returns the combined String version of the generic
* @return the combined String version of the generic
*/
    public String toString() {
        return (getTerm() + "\t" + getStatement() + "\t" + getCfScore());
    }
}
