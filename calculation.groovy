import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.Issue

def cfManager = ComponentAccessor.customFieldManager

//defining initial fields 

def pTrasnferType = cfManager.getCustomFieldObject("customfield_13507")
def catField = cfManager.getCustomFieldObject("customfield_13517")
def tNature = cfManager.getCustomFieldObject("customfield_13514")
def skuCount = cfManager.getCustomFieldObject("customfield_14503")

//defining numeric assets

def numTType = 0
def numCatField = 0
def numtNature = 0

//getting values as list

def pTransferTypeValue = issue.getCustomFieldValue(pTrasnferType) as List
def catFieldValue = issue.getCustomFieldValue(catField) as List
def tNatureValue = issue.getCustomFieldValue(tNature) as List
def skuCountValue = issue.getCustomFieldValue(skuCount) as int

// Mapping options with numbers
switch (pTransferTypeValue)
{
    case 'F&A':
    numTType = 0.8
        break
    case 'Make':
    numTType = 1
        break
    case 'Make & F&A':
    numTType = 1.25
        break
    default:
        numTType = 0
}
switch (catFieldValue)
{
    case 'Makeup I':
    numCatField = 1
        break
    case 'Makeup II':
    numCatField = 0.8
        break
    case 'SkinCare':
    numCatField = 1
        break
    case 'Fragrance':
    numCatField = 1
        break
    case 'Haircare':
    numCatField = 1
        break
    default:
        numCatField = 0
}
switch (tNatureValue)
{
    case 'Disc':
    numtNature = 1
        break
    case 'Non-Disc':
    numtNature = 1.2
        break
    case 'OTC Non-Disc':
    numtNature = 1.5
        break
    default:
        numtNature = 0
}

//multiplication

def mult = numTType * numtNature * numCatField * skuCountValue
if (mult != 0) {
    return mult
}
else {
    return 0
}