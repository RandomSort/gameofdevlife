# Simulation Description

In this document I try to describe the first setup that would give some valuable simulation.


I want to be able see how much value I gain from production over time.

## The application

We simulate a webapplication.

We have one environment _"production"_.

We have one _integration_ branch _"master"_.

_"production"_ automatically deploys the newest commit on _"master"_.

## Configuration

### Bug penalty

How much to penalize for having bugs in production.

### How often do a developer deliver work

How much work or how many steps between deliveries

### What is the probability of a merge conflict (Only relevant for 1+ developers )

When I try to deliver what is the probability that my delivery will have a merge conflict with the previous delivery

### How much effort does it take to fix a bug

If we implement a bug fixing strategy, how much effort does it take to remove a bug

## Desired output

After N steps how much value is in production?
How many bugs?
