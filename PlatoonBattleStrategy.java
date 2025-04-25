import java.util.*;

class Platoon {
    String type;
    int count;

    Platoon(String type, int count) {
        this.type = type;
        this.count = count;
    }

    @Override
    public String toString() {
        return type + "#" + count;
    }
}

public class PlatoonBattleStrategy {

    static Map<String, List<String>> advantageMap = new HashMap<>() {{
        put("Militia", Arrays.asList("Spearmen", "LightCavalry"));
        put("Spearmen", Arrays.asList("LightCavalry", "HeavyCavalry"));
        put("LightCavalry", Arrays.asList("FootArcher", "CavalryArcher"));
        put("HeavyCavalry", Arrays.asList("Militia", "FootArcher", "LightCavalry"));
        put("CavalryArcher", Arrays.asList("Spearmen", "HeavyCavalry"));
        put("FootArcher", Arrays.asList("Militia", "CavalryArcher"));
    }};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String myLine = sc.nextLine();
        String enemyLine = sc.nextLine();

        List<Platoon> myPlatoons = parsePlatoons(myLine);
        List<Platoon> enemyPlatoons = parsePlatoons(enemyLine);

        List<List<Platoon>> permutations = new ArrayList<>();
        generatePermutations(myPlatoons, 0, permutations);

        for (List<Platoon> perm : permutations) {
            int wins = 0;
            for (int i = 0; i < 5; i++) {
                if (doesWin(perm.get(i), enemyPlatoons.get(i))) {
                    wins++;
                }
            }
            if (wins >= 3) {
                System.out.println(String.join(";", perm.stream().map(Platoon::toString).toArray(String[]::new)));
                return;
            }
        }

        System.out.println("There is no chance of winning");
    }

    static List<Platoon> parsePlatoons(String line) {
        List<Platoon> result = new ArrayList<>();
        for (String part : line.split(";")) {
            String[] split = part.split("#");
            result.add(new Platoon(split[0], Integer.parseInt(split[1])));
        }
        return result;
    }

    static void generatePermutations(List<Platoon> list, int index, List<List<Platoon>> result) {
        if (index == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < list.size(); i++) {
            Collections.swap(list, i, index);
            generatePermutations(list, index + 1, result);
            Collections.swap(list, i, index);
        }
    }

    static boolean doesWin(Platoon mine, Platoon enemy) {
        int myEffective = mine.count;
        int enemyEffective = enemy.count;

        if (hasAdvantage(mine.type, enemy.type)) {
            myEffective *= 2;
        }
        if (hasAdvantage(enemy.type, mine.type)) {
            enemyEffective *= 2;
        }

        return myEffective > enemyEffective;
    }

    static boolean hasAdvantage(String attacker, String defender) {
        return advantageMap.containsKey(attacker) && advantageMap.get(attacker).contains(defender);
    }
}
