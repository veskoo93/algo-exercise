package com.rqpa.algo.factorization;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

public class FactorizationTestCase
{
    private static final Logger logger = getLogger(FactorizationTestCase.class);

    @Test
    public void testFactorize220()
    {
        doTestSmallNumberFactorization(220l);
    }

    @Test
    public void testFactorize284()
    {
        doTestSmallNumberFactorization(284);
    }

    @Test
    public void testFactorize1375()
    {
        doTestSmallNumberFactorization(1375);
    }

    @Test
    public void testFactorizeRandomLargeLong()
    {
        doTestFactorization(4962768465676381896l,
                1l,
                2l,
                3l,
                4l,
                6l,
                8l,
                12l,
                24l,
                31l,
                62l,
                73l,
                93l,
                124l,
                146l,
                186l,
                199l,
                219l,
                248l,
                292l,
                372l,
                398l,
                438l,
                584l,
                597l,
                744l,
                796l,
                876l,
                1194l,
                1592l,
                1752l,
                2263l,
                2388l,
                4526l,
                4776l,
                6169l,
                6789l,
                9052l,
                12338l,
                13578l,
                14527l,
                18104l,
                18507l,
                24676l,
                27156l,
                29054l,
                37014l,
                43581l,
                49352l,
                54312l,
                58108l,
                66347l,
                74028l,
                87162l,
                116216l,
                132694l,
                148056l,
                174324l,
                199041l,
                265388l,
                348648l,
                398082l,
                450337l,
                530776l,
                796164l,
                900674l,
                1351011l,
                1592328l,
                1801348l,
                2056757l,
                2702022l,
                3602696l,
                4113514l,
                4843331l,
                5404044l,
                6170271l,
                6920761l,
                8227028l,
                9686662l,
                10808088l,
                12340542l,
                13203053l,
                13841522l,
                14529993l,
                16454056l,
                19373324l,
                20762283l,
                24681084l,
                26406106l,
                27683044l,
                29059986l,
                38746648l,
                39609159l,
                41524566l,
                49362168l,
                52812212l,
                55366088l,
                58119972l,
                79218318l,
                83049132l,
                105624424l,
                116239944l,
                150143261l,
                158436636l,
                166098264l,
                214543591l,
                300286522l,
                316873272l,
                409294643l,
                429087182l,
                450429783l,
                505215553l,
                600573044l,
                643630773l,
                818589286l,
                858174364l,
                900859566l,
                963822869l,
                1010431106l,
                1201146088l,
                1227883929l,
                1287261546l,
                1377231439l,
                1515646659l,
                1637178572l,
                1716348728l,
                1801719132l,
                1927645738l,
                2020862212l,
                2455767858l,
                2574523092l,
                2754462878l,
                2891468607l,
                3031293318l,
                3274357144l,
                3603438264l,
                3855291476l,
                4041724424l,
                4131694317l,
                4911535716l,
                5149046184l,
                5508925756l,
                5782937214l,
                6062586636l,
                7710582952l,
                8263388634l,
                9823071432l,
                11017851512l,
                11565874428l,
                12125173272l,
                15661682143l,
                16526777268l,
                23131748856l,
                29878508939l,
                31323364286l,
                33053554536l,
                42694174609l,
                46985046429l,
                59757017878l,
                62646728572l,
                85388349218l,
                89635526817l,
                93970092858l,
                100537895047l,
                119514035756l,
                125293457144l,
                128082523827l,
                170776698436l,
                179271053634l,
                187940185716l,
                201075790094l,
                239028071512l,
                256165047654l,
                301613685141l,
                341553396872l,
                358542107268l,
                375880371432l,
                402151580188l,
                459171730067l,
                512330095308l,
                603227370282l,
                717084214536l,
                804303160376l,
                918343460134l,
                1024660190616l,
                1206454740564l,
                1377515190201l,
                1836686920268l,
                2412909481128l,
                2755030380402l,
                3116674746457l,
                3673373840536l,
                5510060760804l,
                6233349492914l,
                9350024239371l,
                11020121521608l,
                12466698985828l,
                14234323632077l,
                18700048478742l,
                24933397971656l,
                28468647264154l,
                33519536294891l,
                37400096957484l,
                42702970896231l,
                56937294528308l,
                67039072589782l,
                74800193914968l,
                85405941792462l,
                91375174283333l,
                100558608884673l,
                113874589056616l,
                134078145179564l,
                170811883584924l,
                182750348566666l,
                201117217769346l,
                268156290359128l,
                274125522849999l,
                341623767169848l,
                365500697133332l,
                402234435538692l,
                548251045699998l,
                731001394266664l,
                804468871077384l,
                1039105625141621l,
                1096502091399996l,
                2078211250283242l,
                2193004182799992l,
                2832630402783323l,
                3117316875424863l,
                4156422500566484l,
                5665260805566646l,
                6234633750849726l,
                6670387722683309l,
                8312845001132968l,
                8497891208349969l,
                11330521611133292l,
                12469267501699452l,
                13340775445366618l,
                16995782416699938l,
                20011163168049927l,
                22661043222266584l,
                24938535003398904l,
                26681550890733236l,
                33991564833399876l,
                40022326336099854l,
                53363101781466472l,
                67983129666799752l,
                80044652672199708l,
                160089305344399416l,
                206782019403182579l,
                413564038806365158l,
                620346058209547737l,
                827128077612730316l,
                1240692116419095474l,
                1654256155225460632l,
                2481384232838190948l,
                4962768465676381896l);
    }

    private void doTestSmallNumberFactorization(long n)
    {
        Set<Long> divisors = new TreeSet<>();
        for (long i = 1; i <= n; i++)
        {
            if (n % i == 0)
            {
                divisors.add(i);
            }
        }
        doTestFactorization(n, divisors);
    }

    private void doTestFactorization(long n, Long... expectedDivisors)
    {
        doTestFactorization(n, Set.of(expectedDivisors));
    }

    private void doTestFactorization(long n, Set<Long> expectedDivisors)
    {
        logger.trace("Expected divisors of {} : {}", n, expectedDivisors);
        Set<Long> actualDivisors = Factorization.getDivisors(n);
        logger.trace("Actual divisors of {} : {}", n, actualDivisors);
        Assertions.assertEquals(expectedDivisors.size(), actualDivisors.size());
        Assertions.assertTrue(actualDivisors.containsAll(expectedDivisors));
    }

    @Test
    public void testPrimeFactorizationOf220()
    {
        doTestPrimeFactorization(220, 2, 2, 5, 1, 11, 1);
    }

    @Test
    public void testPrimeFactorizationOfRandomLargeLong()
    {
        doTestPrimeFactorization(4962768465676381896l,
                2, 3,
                3, 1,
                31, 1,
                73, 1,
                199, 1,
                66347, 1,
                6920761, 1);
    }

    private void doTestPrimeFactorization(long toFactorize, long... primesAndPowers)
    {
        Assertions.assertEquals(0, primesAndPowers.length % 2);
        Map<Long, Long> factored = Factorization.factorizeToPrimes(toFactorize);
        Assertions.assertEquals(primesAndPowers.length / 2, factored.size());
        assertPrimeFactors(toFactorize, factored);
        for (int i = 0; i < primesAndPowers.length; i += 2)
        {
            long prime = primesAndPowers[i];
            long power = primesAndPowers[i + 1];
            Assertions.assertEquals(power, factored.get(prime));
        }
    }

    private void assertPrimeFactors(long expected, Map<Long, Long> factors)
    {
        long actual = 1l;
        for (Map.Entry<Long, Long> primeToPowerEntry : factors.entrySet())
        {
            long prime = primeToPowerEntry.getKey();
            long power = primeToPowerEntry.getValue();
            for (long i = 0; i < power; i++)
            {
                actual *= prime;
            }
        }
        Assertions.assertEquals(expected, actual);
    }

}
