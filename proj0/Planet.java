public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /** Gravitational constant */
    public static final double G = 6.67e-11;


    /** Initialize a Planet Object with six inputs.
     * @param xP: x position of the Planet.
     * @param yP: y position of the Planet.
     * @param xV: velocity in x direction of the Planet.
     * @param yV: velocity in y direction of the Planet.
     * @param m: mass of the Planet.
     * @param img: name of the file that corresponds to the Planet.
     */
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** Initialize a new Planet object having same parameters as the input Planet object.
     *
     * @param p: Planet object.
     */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /** Calculate the distance between two planets.
     * @param p: palnet object.
     * @return distiance between two planets.
     */
    public double calcDistance(Planet p) {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /** Calculate the force exerted by a planet on the other planet.
     * @param p: planet object.
     * @return force exerted by a planet on the other planet.
     */
    public double calcForceExertedBy(Planet p) {
        double dist = this.calcDistance(p);
        double force = G * this.mass * p.mass / (dist * dist);
        return force;
    }

    /** Calculate the net force exerted by a planet on the other planet in the x direction.
     *
     * @param p: planet object.
     * @return: the net force exerted by a planet on the other planet in the x direction.
     */
    public double calcForceExertedByX(Planet p) {
        double force = this.calcForceExertedBy(p);
        double dist = this.calcDistance(p);
        return force * (p.xxPos - this.xxPos) / dist;
    }

    /** Calculate the net force exerted by a planet on the other planet in the y direction.
     *
     * @param p: planet object.
     * @return: the net force exerted by a planet on the other planet in the y direction.
     */
    public double calcForceExertedByY(Planet p) {
        double force = this.calcForceExertedBy(p);
        double dist = this.calcDistance(p);
        return force * (p.yyPos - this.yyPos) / dist;
    }

    /** Calculate net force(X direction) of a series of planets on a planet.
     * @param allPlanets: a list of planets.
     * @return: the net force exerted on a planet by a series of palnets.
     */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netforce = 0.0;
        for (Planet p : allPlanets) {
            if (p.equals(this)) {
                continue;
            }
            netforce += this.calcForceExertedByX(p);
        }
        return netforce;
    }

    /** Calculate net force(Y direction) of a series of planets on a planet.
     * @param allPlanets: a list of planets.
     * @return: the net force exerted on a planet by a series of planets.
     */
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netforce = 0.0;
        for (Planet p : allPlanets) {
            if (p.equals(this)) {
                continue;
            }
            netforce += this.calcForceExertedByY(p);
        }
        return netforce;
    }

    /** Update the velocity and position according to the forces and duration given.
     * @param dt: duration of the time the force is exerted.
     * @param xForce: the force exerted in the x direction.
     * @param yForce: the force exerted in the y direction.
     */
    public void update(double dt, double xForce, double yForce) {
        double aX = xForce / this.mass;
        double aY = yForce / this.mass;

        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    /** Draw a planet at its appropriate position.
     */
    public void draw() {
        String folder = "images/";
        String path = folder + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, path);
    }
}
