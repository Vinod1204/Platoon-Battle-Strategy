#  Platoon Battle Strategy - Java Solution

##  Problem Overview

In a medieval battlefield scenario, you're a king commanding 5 platoons attacking 5 enemy platoons simultaneously. Each platoon is composed of a **specific class of soldiers** and a **number of soldiers**.

You **know the composition of the enemy platoons**, and you must determine the best **arrangement of your platoons** to **win at least 3 out of 5 battles**.

---

## Unit Classes and Advantage Rules

There are 6 types of platoons:

- Militia  
- Spearmen  
- LightCavalry  
- HeavyCavalry  
- FootArcher  
- CavalryArcher

Each class has an advantage over specific other classes. If a platoon has an advantage over its enemy:
- **Its effective strength is doubled**

### Class Advantage Chart:

| Class           | Has Advantage Over                     |
|----------------|------------------------------------------|
| Militia        | Spearmen, LightCavalry                   |
| Spearmen       | LightCavalry, HeavyCavalry               |
| LightCavalry   | FootArcher, CavalryArcher                |
| HeavyCavalry   | Militia, FootArcher, LightCavalry        |
| CavalryArcher  | Spearmen, HeavyCavalry                   |
| FootArcher     | Militia, CavalryArcher                   |

---

##  Objective

Reorder your 5 platoons to **maximize win rate**.  
Each battle is 1v1 based on the current positions.

### Victory Rules:
- Equal soldier count → Draw
- Your count > Enemy → Win
- Your count < Enemy → Loss
- If advantage applies, **double** the respective side's soldier count.

You must find **any arrangement** that gives **≥3 wins**.

---

## Input Format

Input is taken in **two lines**:

Example :
Spearmen#10;Militia#30;FootArcher#20;LightCavalry#1000;HeavyCavalry#120
Militia#10;Spearmen#10;FootArcher#1000;LightCavalry#120;CavalryArcher#100

## Output Format

Example:
Militia#30;FootArcher#20;Spearmen#10;LightCavalry#1000;HeavyCavalry#120