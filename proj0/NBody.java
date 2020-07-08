public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int num = in.readInt();
        double radius = in.readDouble();

        Planet[] allPlanets = new Planet[num];
        for (int i = 0; i < num; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            allPlanets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
        }
        return allPlanets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);

        /* Draw the background. */
        String bgFilename = "images/starfield.jpg";
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, bgFilename);

        /* Draw all planets. */
        for (Planet p : allPlanets) {
            p.draw();
        }

        /* Creating animation. */
        StdDraw.enableDoubleBuffering();
        double timeVar = 0.0;
        int numPlanet = allPlanets.length;
        double[] xForces= new double[numPlanet];
        double[] yForces = new double[numPlanet];
        while (timeVar < T) {
            /* Calculate net force exerted on each planet. */
            for (int i = 0; i < numPlanet; i++) {
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }
            /*Update on each of the planet. */
            for (int i = 0; i < numPlanet; i++) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }

            /* Draw background and all planets. */
            StdDraw.picture(0, 0, bgFilename);
            for (Planet p : allPlanets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            timeVar += dt;
        }

        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                    allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }
    }
}
