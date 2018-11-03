var $proposer = $('#proposer')
var $email = $('#email')
var $tel = $('#tel')
var $incity = $('#incity')
var incity = ''
var $loanamount = $('#loanamount')
var ishouseproperty = '0'
var machineChecked = false
var applying = false

$(function() {
  // preloader
  $('#preloader')
    .delay(600)
    .fadeOut()

  ;('use strict')

  // Banner slogan animations
  setTimeout(function() {
    $('#slogan')
      .removeClass('hidden')
      .addClass('animated bounceInLeft')
  }, 1000)

  setTimeout(function() {
    $('#slogan-em')
      .removeClass('no-visible')
      .addClass('animated bounceInDown')
  }, 1300)

  setTimeout(function() {
    $('#slogan-adv')
      .removeClass('hidden')
      .addClass('animated fadeInUp')
  }, 1000)

  ///////////////////////////
  // Scrollspy
  $('body').scrollspy({
    target: '#nav',
    offset: $(window).height() / 2
  })

  ///////////////////////////
  // Smooth scroll
  $("#nav .main-nav a[href^='#'],.slogan-link,.outline-btn").on(
    'click',
    function(e) {
      e.preventDefault()
      var hash = this.hash
      $('html, body').animate(
        {
          scrollTop: $(this.hash).offset().top
        },
        600
      )
    }
  )

  $('#back-to-top').on('click', function() {
    $('body,html').animate(
      {
        scrollTop: 0
      },
      600
    )
  })

  ///////////////////////////
  // Btn nav collapse
  $('#nav .nav-collapse').on('click', function() {
    $('#nav').toggleClass('open')
  })

  $('body').on('click', function(e) {
    if ($(e.target).parents('#nav').length === 0) {
      $('#nav').removeClass('open')
    }
  })

  ///////////////////////////
  // Mobile dropdown
  $('.has-dropdown a').on('click', function() {
    $(this)
      .parent()
      .toggleClass('open-drop')
  })

  ///////////////////////////
  // On Scroll
  $(window).on('scroll', function() {
    var wScroll = $(this).scrollTop()

    // Fixed nav
    wScroll > 1
      ? $('#nav').addClass('fixed-nav')
      : $('#nav').removeClass('fixed-nav')

    // Back To Top Appear
    wScroll > 700 ? $('#back-to-top').fadeIn() : $('#back-to-top').fadeOut()
  })

  ///////////////////////////
  // magnificPopup
  $('.work').magnificPopup({
    delegate: '.lightbox',
    type: 'image'
  })

  ///////////////////////////
  // Owl Carousel
  $('#about-slider').owlCarousel({
    items: 1,
    loop: true,
    margin: 15,
    nav: true,
    navText: [
      '<i class="fa fa-angle-left"></i>',
      '<i class="fa fa-angle-right"></i>'
    ],
    dots: true,
    autoplay: true,
    animateOut: 'fadeOut'
  })

  $('#testimonial-slider').owlCarousel({
    loop: true,
    margin: 20,
    dots: true,
    nav: false,
    autoplay: true,
    responsive: {
      0: {
        items: 1
      },
      992: {
        items: 2
      }
    }
  })

  $('.checkspan').bind('click', function() {
    var ul = $(this).siblings('ul')
    if (ul.is(':hidden')) {
      ul.slideDown('400', function() {
        $(this)
          .find('li')
          .bind('click', function() {
            var selectLi = $(this).text()
            $('#select span').text(selectLi)
            $('#select ul').slideUp(400)
          })
        ul.mouseleave(function() {
          $('#select ul').slideUp(400)
        })
      })
    } else {
      $(this)
        .siblings('ul')
        .slideUp(400)
    }
  })

  // Incity data load
  getCityList()

  // Form init
  $('input.choice-for-house').iCheck({
    checkboxClass: 'icheckbox_minimal-blue',
    radioClass: 'iradio_minimal-blue',
    increaseArea: '20%' // optional
  })
  $('input.choice-for-house').on('ifChecked', function(event) {
    ishouseproperty = event.target.value
  })

  // Baidu Map init
  ShowMap(
    '121.450943,31.250148',
    '冠润科技',
    '上海市普陀区江宁路1158号友力国际大厦2702室',
    '021-80126312',
    '',
    '18'
  )

  $('body').on('click', '#apply', function() {
    setApplyStatus(true)
    applyCheck()
  })

  // Verify first init
  updateVerify()

  // window size change update
  $(window).resize(function() {
    updateVerify()
  })

  document.addEventListener('touchstart', function(event) {
    if (event.touches.length > 1) {
      event.preventDefault()
    }
  })

  var lastTouchEnd = 0

  document.addEventListener(
    'touchend',
    function(event) {
      var now = new Date().getTime()

      if (now - lastTouchEnd <= 300) {
        event.preventDefault()
      }

      lastTouchEnd = now
    },
    false
  )
})

function ShowMap(zuobiao, name, address, phone, chuanzhen, zoom) {
  var arrzuobiao = zuobiao.split(',')
  var map = new BMap.Map('map')
  map.centerAndZoom(new BMap.Point(arrzuobiao[0], arrzuobiao[1]), zoom)
  map.addControl(new BMap.NavigationControl())
  var marker = new BMap.Marker(new BMap.Point(arrzuobiao[0], arrzuobiao[1]))
  map.addOverlay(marker)
  var infoWindow = new BMap.InfoWindow(
    '<p style="color: #bf0008;font-size:20px;">' +
      name +
      '</p><p>地址：' +
      address +
      '</p><p>电话：' +
      phone +
      '</p>'
  )
  marker.addEventListener('click', function() {
    this.openInfoWindow(infoWindow)
  })
  marker.openInfoWindow(infoWindow)
}

function getCityList() {
  $.post('/homeset/city/listCity.json', { IP: '127.0.0.1' }, function(
    response
  ) {
    var tmp = ''
    response.reObj.forEach(function(item, index, arr) {
      if (index === 0) {
        incity = item.code
        tmp +=
          '<option value="' +
          item.code +
          '" selected>' +
          item.city +
          '</option>'
      } else {
        tmp += '<option value="' + item.code + '">' + item.city + '</option>'
      }
    })
    $('#filter-list').html(tmp)

    $('.filter-box').selectFilter({
      callBack: function(val) {
        // $("#incity").val(val);
        incity = val
      }
    })
  })
}

function applyCheck() {
  var checked = true
  proposer = $proposer.val()
  email = $email.val()
  tel = $tel.val()
  loanamount = $loanamount.val()

  if (!proposer) {
    layer.msg('请输入姓名!', { icon: 7 })
    setApplyStatus(false)
    return
  } else {
    if (proposer.length > 4 || proposer.length < 2) {
      layer.msg('姓名格式不正确!', { icon: 7 })
      setApplyStatus(false)
      return
    }
  }

  if (!email) {
    layer.msg('请输入电子邮箱!', { icon: 7 })
    setApplyStatus(false)
    return
  } else {
    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
    if (!reg.test(email)) {
      layer.msg('电子邮箱格式不正确!', { icon: 7 })
      setApplyStatus(false)
      return
    }
  }

  if (!tel) {
    layer.msg('请输入手机号码!', { icon: 7 })
    setApplyStatus(false)
    return
  } else {
    var myreg = /^[1][3,4,5,6,7,8][0-9]{9}$/

    if (!myreg.test(tel)) {
      layer.msg('手机号码格式不正确!', { icon: 7 })
      setApplyStatus(false)
      return
    }
  }

  if (!incity) {
    layer.msg('请选择所在城市!', { icon: 7 })
    return
  }

  if (!loanamount) {
    layer.msg('请输入贷款金额!', { icon: 7 })
    setApplyStatus(false)
    return
  } else {
    if (Number(loanamount) <= 0) {
      layer.msg('请输入正确的贷款金额!', { icon: 7 })
      setApplyStatus(false)
      return
    }
  }

  if (!machineChecked) {
    layer.msg('请完成校验!', { icon: 7 })
    setApplyStatus(false)
    return
  }
  var data = {
    proposer: proposer,
    email: email,
    tel: tel,
    incity: incity,
    ishouseproperty: ishouseproperty,
    loanamount: loanamount
  }
  apply(data)
}

function setApplyStatus(status) {
  if (status) {
    if (!$('#apply').hasClass('applying')) {
      applying = true
      $('#apply')
        .prop('disabled', true)
        .addClass('applying')
    }
  } else {
    if ($('#apply').hasClass('applying')) {
      applying = false
      $('#apply')
        .prop('disabled', false)
        .removeClass('applying')
    }
  }
}

function apply(data) {
  $.post('/homeset/proposer/insertApply.json', data, function(response) {
    if (response.status === 200) {
      layer.msg(response.msg, { icon: '1' })
      clearForm('#apply-form')
      $('#icheck-yes').iCheck('check')
      incity = $('#apply-form select option:first-of-type').val()
      ishouseproperty = '0'
      resetVerify()
    } else {
      layer.msg(response.msg, { icon: '2' })
    }
    setApplyStatus(false)
  })
}

function clearForm(target) {
  $(target)
    .find('input:not(.filter-title):not([type="radio"])')
    .val('')
  $(target)
    .find('.filter-title')
    .val($('select option:first-of-type', target).text())
  $(target)
    .find('select option:first-of-type')
    .prop('selected', true)
  $(target)
    .find('input[type="radio"]:first-of-type')
    .prop('checked', true)
}

function updateVerify() {
  if ($(document.body).width() < 430) {
    resetVerify('200px', '36px')
  } else {
    resetVerify()
  }
}

function resetVerify(width, height) {
  machineChecked = false
  $('#verify').empty()
  $('#verify').slideVerify({
    type: 1, //类型
    vOffset: 5, //误差量，根据需求自行调整
    barSize: {
      width: width ? width : '300px',
      height: height ? height : '40px'
    },
    ready: function() {},
    success: function() {
      machineChecked = true
      layer.msg('验证成功!', { icon: 1 })
    },
    error: function() {
      layer.msg('验证失败请重试!', { icon: 2 })
    }
  })
}
