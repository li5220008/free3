package dto.financeana;

/**
 * 来自于STK_FIN_DebtPay表
 * User: wenzhihong
 * Date: 12-12-21
 * Time: 下午2:19
 */
public class DebtPayItem {
    public String enddateStr;
    public double currentRatio;/*流动比率*/
    public double quickRatio;/*速动比率*/
    public double conservativeQuickRatio;/*保守速动比率*/
    public double cashRatio;/*现金比率*/
    public double workingCapitalToliability;/*营运资金与借款比*/
    public double interestCoverageRatioB;/*利息保障倍数*/
    public double currentLiabilityCoverage;/*经营活动产生的现金流量净额／流动负债*/
    public double interestCoverageRatioC;/*现金流利息保障倍数*/
    public double maturingLiabilityCoverage;/*现金流到期债务保障倍数*/
    public double assetLiabilityRatio;/*资产负债率*/
    public double longtermLiabilityToAsset;/*长期借款与总资产比*/
    public double liabilityToTangibleAsset;/*有形资产负债率*/
    public double interesLiabilityToTangib;/*有形资产带息债务比*/
    public double equityMultiplier;/*权益乘数*/
    public double debtEquityRatio;/*产权比率*/
    public double equityToDebtRatio;/*权益对负债比率*/
    public double longtermAssetLiabilityRatio;/*长期资本负债率*/
    public double longtermLiabilityToEquity;/*长期负债权益比率*/
    public double longtermLiabilityToWorking;/*长期债务与营运资金比率*/
    public double eBITDAToLiability;/*息税折旧摊销前利润／负债合计*/
    public double liabilityCoverage;/*经营活动产生的现金流量净额／负债合计*/
    public double interesLiabilityCoverage;/*经营活动产生的现金流量净额／带息债务*/
    public double liabilityToMarketValue;/*负债与权益市价比率*/
}
